package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;


    @Test
    public void shouldGetListOfTasks() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task((long)1, "task1", "abcd"));
        taskList.add(new Task((long)2, "task2", "bjdahgs"));

        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(new TaskDto((long)1, "task1", "abcd"));
        taskListDto.add(new TaskDto((long)2, "task2", "bjdahgs"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("task1")))
                .andExpect(jsonPath("$[1].content", is("bjdahgs")));

    }

    @Test
    public void shouldGetEmptyList() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskListDto = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given

        Task taskMapped = new Task(3L, "task3", "new task 3");

        when(service.saveTask(taskMapped)).thenReturn(taskMapped);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskMapped);

        //When
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
               //.andReturn().getResponse().getContentAsString();


    }

    @Test
    public void shouldGetTask() throws Exception{
        //Given
        Long taskId = (long)3;
        Task task = new Task(taskId, "task3", "task3_test");
        TaskDto taskDto = new TaskDto(taskId, "task3", "task3_test");


        when(service.getOneTask(taskId)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/3")
               // .param("taskId", "3")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("task3")));
    }


    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto updatedTaskDto = new TaskDto(3L, "task3", "task3_update");
        Task updatedTask = new Task(3L, "task3", "task3_update");


        when(taskMapper.mapToTaskDto(updatedTask)).thenReturn(updatedTaskDto);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);

        //When & Then

        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception{
        //Given


        //When & Then
        mockMvc.perform(delete("/v1/tasks/4")
                //.param("taskId", "4")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}