/*
   Copyright 2017 Ericsson AB.
   For a full list of individual contributors, please see the commit history.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlowTestTestExecution extends FlowTestBase {

     private static Logger log = LoggerFactory.getLogger(FlowTest.class);
     static protected String inputFilePath = "src/test/resources/aggregatedExternalComposition.json";
     static protected String jsonFilePath = "src/test/resources/TestExecutionTestEvents.json";
     static protected String rulePath = "src/test/resources/TestActivityObjectRules.json";

     @Autowired
     RulesHandler rulesHandler;

        protected void setSpecificTestCaseParameters(){
            setJsonFilePath(jsonFilePath);
            rulesHandler.setRulePath(rulePath);
        }

        protected ArrayList<String> getEventNamesToSend() {
            ArrayList<String> eventNames = new ArrayList<>();
            eventNames.add("event_EiffelActivityTriggeredEvent");
//            eventNames.add("event_EiffelActivityStartedEvent");
//            eventNames.add("event_EiffelTestExecutionRecipeCollectionCreatedEvent");
//            eventNames.add("event_EiffelTestSuiteStartedEvent");
//            eventNames.add("event_EiffelTestCaseTriggeredEvent");
//            eventNames.add("event_EiffelTestCaseTriggeredEvent_2");
//            eventNames.add("event_EiffelTestCaseStartedEvent");
//            eventNames.add("event_EiffelTestCaseStartedEvent_2");
//            eventNames.add("event_EiffelTestCaseFinishedEvent");
//            eventNames.add("event_EiffelTestCaseFinishedEvent_2");
//            eventNames.add("event_EiffelActivityFinishedEvent");
            return eventNames;
        }

        protected void checkResult() {
            try {
                String expectedDocuments = FileUtils.readFileToString(new File(inputFilePath));
                ObjectMapper objectmapper = new ObjectMapper();
                JsonNode expectedJsons = objectmapper.readTree(expectedDocuments);
                JsonNode expectedJson1 = expectedJsons.get(0);
                JsonNode expectedJson2 = expectedJsons.get(1);
                JsonNode expectedJson3 = expectedJsons.get(2);
                String document1 = objectHandler.findObjectById("6acc3c87-75e0-4b6d-88f5-b1a5d4e62b43");
                String document2 = objectHandler.findObjectById("cfce572b-c3j4-441e-abc9-b62f48080ca2");
                String document3 = objectHandler.findObjectById("cfre572b-c3j4-4d1e-ajc9-b62f45080ca2");
                JsonNode actualJson1 = objectmapper.readTree(document1);
                JsonNode actualJson2 = objectmapper.readTree(document2);
                JsonNode actualJson3 = objectmapper.readTree(document3);
                int i = 0;
            } catch (Exception e) {
                log.info(e.getMessage(),e);
            }

        }
}
