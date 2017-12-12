package com.test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.alibaba.fastjson.JSON;
import com.springboot.entity.User;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import springfox.documentation.staticdocs.SwaggerResultHandler;
import springfox.documentation.swagger2.web.Swagger2Controller;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration  
@SpringBootTest(classes = SwaggerApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SwaggerApplicationTests {

	private String snippetDir = "target/generated-snippets";
	private String outputDir = "target/asciidoc";

	@Autowired
	private MockMvc mockMvc;

	@After
	public void Test() throws Exception {
		// 得到swagger.json,写入outputDir目录中
		mockMvc.perform(get(Swagger2Controller.DEFAULT_URL).accept(MediaType.APPLICATION_JSON))
				.andDo(SwaggerResultHandler.outputDirectory(outputDir).build()).andExpect(status().isOk()).andReturn();

		// 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
		// 这个outputDir必须和插件里面<generated></generated>标签配置一致
		Swagger2MarkupConverter.from(outputDir + "/swagger.json").withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
				.withExamples(snippetDir).build().intoFolder(outputDir);// 输出
	}

	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/student").param("name", "xxx").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcRestDocumentation.document("getStudent", preprocessResponse(prettyPrint())));

		User student = new User();
		student.setUserName("11111111111");

		mockMvc.perform(post("/addStudent").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(student))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andDo(MockMvcRestDocumentation.document("addStudent", preprocessResponse(prettyPrint())));
	}

}
