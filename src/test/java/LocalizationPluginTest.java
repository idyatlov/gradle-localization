import io.github.idyatlov.localization.LocalizationPlugin;
import io.github.idyatlov.localization.OneSkyLocalizationTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Jedo on 08.08.2016.
 */
public class LocalizationPluginTest {

  @Test
  public void localization_plugin_should_add_task_to_project() {
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply(LocalizationPlugin.class);
    Task task = project.getTasks().getByName("onesky");
    assertTrue(task instanceof OneSkyLocalizationTask);
    assertTrue(!task.getActions().isEmpty());
  }
}
