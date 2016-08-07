package io.github.idyatlov.localization;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class LocalizationPlugin implements Plugin<Project> {
  @Override
  public void apply(Project project) {
    project.getExtensions().create("localization", LocalizationExtension.class);
    project.getTasks().create("onesky", OneSkyLocalizationTask.class);
  }
}