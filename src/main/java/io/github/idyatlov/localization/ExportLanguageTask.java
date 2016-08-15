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

import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.incremental.IncrementalTaskInputs;

import java.io.File;
import java.util.List;

/**
 * Created by Jedo on 16.08.2016.
 */
public class ExportLanguageTask extends BaseTask {

  @InputFiles
  private List<File> getInputFiles() {
    return inputFiles;
  }

  @OutputDirectory
  File outputDirectory;

  @TaskAction
  protected void execute(IncrementalTaskInputs incrementalTaskInputs) {

    init();

    if (!incrementalTaskInputs.isIncremental()) {
      getProject().delete(outputDirectory.listFiles());
    }

    incrementalTaskInputs.outOfDate(inputFileDetails -> {

    });
  }


}
