package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anatolii_Hanziuk on 1/19/2017.
 */
public class StoryPathConverter {

    public static List<String> convertStringToListOfStoryPathes(String story) {
        List<String> storyPathes = new ArrayList<>();
        Arrays.asList(story.split("\\s*,\\s*"))
                .stream().forEach(storyName -> storyPathes.add("**/".concat(storyName).concat(".story")));
        return storyPathes;
    }
}
