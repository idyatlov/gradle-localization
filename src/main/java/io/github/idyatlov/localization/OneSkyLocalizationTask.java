package io.github.idyatlov.localization;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * Created by Jedo on 08.08.2016.
 */
public class OneSkyLocalizationTask extends DefaultTask {

  /**
   * Default task action.
   **/
  @TaskAction
  public void localize() {
    LocalizationExtension extension = getProject().getExtensions()
        .findByType(LocalizationExtension.class);
    if (extension == null) {
      extension = new LocalizationExtension();
    }
    String oneSkyProjectId = extension.getOneskyProjectId();
    String oneSkyPublic = extension.getOneskyPublic();
    String oneSkySecret = extension.getOneSkySecret();
    System.out.println(oneSkyProjectId);
    System.out.println(oneSkyPublic);
    System.out.println(oneSkySecret);
  }
}
