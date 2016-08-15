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

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class InternationalizationPlugin implements Plugin<Project> {

  public static final String EXTENSION = "onesky";
  public static final String EXPORT = "export";

  @Override
  public void apply(Project project) {
    project.getExtensions().create(EXTENSION, OneskyConfigExtension.class);
    project.getTasks().maybeCreate(EXPORT, ExportLanguageTask.class);
  }
}