package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Category;
import pojo.CreateJson;
import pojo.Tags;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    public static CreateJson getCreateObject() {
        CreateJson createJson = new CreateJson();
        createJson.setId(10);
        createJson.setName("corgi");

        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");
        createJson.setCategory(category);

        List<String> PhotoUrls = new ArrayList<>();
        PhotoUrls.add("umit");
        createJson.setPhotoUrls(PhotoUrls);

        List<Tags> tagsList = new ArrayList<>();
        Tags tags = new Tags(1, "Animal");
        Tags tags2 = new Tags(2, "Pet");
        tagsList.add(tags);
        tagsList.add(tags2);
        createJson.setTags(tagsList);

        createJson.setStatus("available");

        return createJson;
    }

    public static String jsonValueString(CreateJson createJson) throws JsonProcessingException {

        String createBody = new ObjectMapper().writeValueAsString(createJson);
        return createBody;
    }


}
