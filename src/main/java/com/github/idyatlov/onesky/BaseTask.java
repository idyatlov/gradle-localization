/*
 * Copyright 2017 Ivan Dyatlov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.idyatlov.onesky;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskExecutionException;
import org.gradle.internal.impldep.org.apache.commons.codec.binary.Hex;

/**
 * Created by Ivan Dyatlov on 08.08.2016.
 */
public class BaseTask extends DefaultTask {

  protected String oneSkyProjectId;
  protected String oneSkyPublic;
  protected String oneSkySecret;

  protected void init() {

    OneSkyExtension ext = (OneSkyExtension) getProject().getExtensions()
        .findByName(OneskyPlugin.ONESKY);
    if (ext == null) {
      throw new TaskExecutionException(this,
          new IllegalArgumentException("No Onesky extension is found"));
    }

    oneSkyProjectId = ext.projectId;
    if (oneSkyProjectId == null) {
      throw new TaskExecutionException(this,
          new IllegalArgumentException("Need to define Onesky project id"));
    }
    oneSkyPublic = ext.publicKey;
    if (oneSkyPublic == null) {
      throw new TaskExecutionException(this,
          new IllegalArgumentException("Need to define Onesky public key"));
    }
    oneSkySecret = ext.secretKey;
    if (oneSkySecret == null) {
      throw new TaskExecutionException(this,
          new IllegalArgumentException("Need to define Onesky secret key"));
    }
    if (ext.toTranslate == null || "".equals(ext.toTranslate.trim())) {
      throw new TaskExecutionException(this,
          new IllegalArgumentException("Need to define at least one file to translate"));
    }
  }

  protected HashMap<String, String> authParams() {
    MessageDigest md5;
    HashMap<String, String> authParams = new HashMap<>();
    try {
      md5 = MessageDigest.getInstance("MD5");
      long timestampInSeconds = System.currentTimeMillis() / 1000;
      authParams.put("timestamp", String.valueOf(timestampInSeconds));
      byte[] digest = md5.digest((timestampInSeconds + oneSkySecret).getBytes());
      authParams.put("dev_hash", String.valueOf(Hex.encodeHex(digest)));
      authParams.put("api_key", oneSkyPublic);
    } catch (NoSuchAlgorithmException ignored) {
      getLogger().error(ignored.getMessage());
    }
    return authParams;
  }
}
