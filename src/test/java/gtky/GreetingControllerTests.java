/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gtky;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    private static final Map<String, String> initParams = new HashMap<String, String>() {
		private static final long serialVersionUID = -1944803094461989862L;

	{
    	put("initParamName1", "email");
    	put("initParamValue1", "foobar@foo.bar");
    	put("initParamName2", "gameMode");
    	put("initParamValue2", "1");
    }};
    
    private static final Map<String, String> playParams = new HashMap<String, String>() {
		private static final long serialVersionUID = -1246034639002287429L;
	{
    	put("playParamName1", "selection");
    	put("playParamValue1", "1");
    	put("playParamName2", "sessionId");
    	//put("playParamValue2", "1");
    }};
    
    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }
    
    @Test
    public void paramNewgameShouldReturnFreshInstance() throws Exception {
    	
    	
    	
    	this.mockMvc.perform(get("/").param(initParams.get("initParamName1"), initParams.get("initParamValue1")))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.email").value("testemail@email.com"));
    }

}
