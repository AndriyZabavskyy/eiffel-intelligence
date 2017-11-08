package com.ericsson.ei.flowtests;

import com.ericsson.ei.rules.RulesHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlowSourceChangeObject extends FlowTest {
    static protected String inputFilePath = "src/test/resources/aggregatedSourceChangeObject.json";
    static protected String jsonFilePath = "src/test/resources/TestSourceChangeObject.json";
    static protected String rulePath = "src/test/resources/TestSourceChangeObjectRules.json";
    private static Logger log = LoggerFactory.getLogger(FlowTest.class);

    @Autowired
    RulesHandler rulesHandler;

    protected void setSpecificTestCaseParameters() {
        setJsonFilePath(jsonFilePath);
        rulesHandler.setRulePath(rulePath);
    }

    protected ArrayList<String> getEventNamesToSend() {
        ArrayList<String> eventNames = new ArrayList<>();
        eventNames.add("event_EiffelSourceChangeCreatedEvent_3");
        eventNames.add("event_EiffelSourceChangeSubmittedEvent_3");
        eventNames.add("event_EiffelConfidenceLevelModifiedEvent_3");
        eventNames.add("event_EiffelConfidenceLevelModifiedEvent_3_2");
        eventNames.add("event_EiffelActivityTriggeredEvent_3");
        eventNames.add("event_EiffelActivityStartedEvent_3");
        eventNames.add("event_EiffelActivityFinishedEvent_3");
        return eventNames;
    }

    protected void checkResult() {
        try {
            String expectedDocuments = FileUtils.readFileToString(new File(inputFilePath));
            ObjectMapper objectmapper = new ObjectMapper();
            JsonNode expectedJsons = objectmapper.readTree(expectedDocuments);
            JsonNode expectedJson1 = expectedJsons.get(0);
            String document = objectHandler.findObjectById("fb6ef12d-25fb-4d77-b9fd-5fktsrefe66de47");
            JsonNode actualJson = objectmapper.readTree(document);
            assertEquals(expectedJson1.toString().length(), actualJson.toString().length());
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }
}
