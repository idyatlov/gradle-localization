/*
 * Copyright 2016 Ivan Dyatlov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.idyatlov.localization;

import org.gradle.api.DefaultTask;
import org.gradle.internal.impldep.org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jedo on 08.08.2016.
 */
public class BaseTask extends DefaultTask {

  protected String oneSkyProjectId;
  protected String oneSkyPublic;
  protected String oneSkySecret;
  protected List<File> inputFiles;

  protected void init() {
    OneskyConfigExtension extension = getProject().getExtensions()
        .findByType(OneskyConfigExtension.class);
    if (extension == null) {
      extension = new OneskyConfigExtension();
    }
    oneSkyProjectId = extension.getProjectId();
    oneSkyPublic = extension.getPublicKey();
    oneSkySecret = extension.getSecretKey();
    inputFiles = extension.getFiles();
    System.out.println(oneSkyProjectId);
    System.out.println(oneSkyPublic);
    System.out.println(oneSkySecret);
    for (File file : inputFiles) {
      System.out.println(file.getPath());
    }
  }

  protected HashMap<String, String> authParams() {
    MessageDigest md5;
    HashMap<String, String> authParams = new HashMap<>();
    try {
      md5 = MessageDigest.getInstance("MD5");
      long timestampInSeconds = System.currentTimeMillis() / 1000;
      authParams.put("timestamp", String.valueOf(timestampInSeconds));
      authParams.put("dev_hash", String.valueOf(Hex.encodeHex(md5.digest((timestampInSeconds +
          oneSkySecret).getBytes()))));
      authParams.put("api_key", oneSkyPublic);
    } catch (NoSuchAlgorithmException ignored) {
    }
    return authParams;
  }
}
