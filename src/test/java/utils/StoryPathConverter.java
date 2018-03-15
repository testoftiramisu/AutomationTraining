package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoryPathConverter {


  /**
   * Convert property string with to list of story paths
   *
   * @return a List containing String values of Paths to stories.
   */
  public static List<String> convertStringToListOfStoryPaths(String story) {
    List<String> storyPaths = new ArrayList<>();
    Arrays.stream(story.split("\\s*,\\s*"))
        .forEach(storyName -> storyPaths.add("**/".concat(storyName).concat(".story")));
    return storyPaths;
  }
}
