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

import io.github.idyatlov.localization.ExportLanguageTask;
import io.github.idyatlov.localization.InternationalizationPlugin;
import io.github.idyatlov.localization.OneskyConfigExtension;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jedo on 08.08.2016.
 */
public class InternationalizationPluginTest {

  @Test
  public void addingTaskToProject() {
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply(InternationalizationPlugin.class);
    Task task = project.getTasks().getByName(InternationalizationPlugin.EXPORT);
    Object extension = project.getExtensions().findByName(InternationalizationPlugin.EXTENSION);
    assertNotNull(extension);
    assertTrue(extension instanceof OneskyConfigExtension);
    assertTrue(task instanceof ExportLanguageTask);
    assertTrue(!task.getActions().isEmpty());
  }
}
