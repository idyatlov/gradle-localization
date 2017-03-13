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

/**
 * Created by Ivan Dyatlov on 08.08.2016.
 */
public class OneSkyExtension {

  String publicKey;

  String secretKey;

  String projectId;

  String toTranslate;

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getToTranslate() {
    return toTranslate;
  }

  public void setToTranslate(String toTranslate) {
    this.toTranslate = toTranslate;
  }

  @Override
  public String toString() {
    return "OneSkyExtension{" + "publicKey='" + publicKey + '\'' + ", secretKey='" + secretKey
        + '\'' + ", projectId='" + projectId + '\'';
  }
}
