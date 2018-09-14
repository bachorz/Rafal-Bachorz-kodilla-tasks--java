package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    private Gson gson = new Gson();

    @Test
    public void getTasksTest () throws Exception {

        //Give
        List<TaskDto> taskListDto = Arrays.asList(new TaskDto(1L,"Test task 1", "description A"),
                new TaskDto(2L,"Test task 2","description B"));

        List<Task> taskList = Arrays.asList(new Task(1L,"Test task 1", "description A"),
                new Task(2L,"Test task 2","description B"));


        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test task 1")))
                .andExpect(jsonPath("$[0].content", is("description A")));
    }

    @Test
    public void getTaskTest () throws Exception {

        //Give
        TaskDto taskDtoTest = new TaskDto(1L,"Test task 1", "description A");


        when(service.getTask(anyLong())).thenReturn(Optional.of(new Task()));
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDtoTest);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test task 1")))
                .andExpect(jsonPath("$.content", is("description A")));
    }

    @Test
    public void deleteTaskTest () throws Exception {

        //Give
        TaskDto taskDtoTest = new TaskDto(1L,"Test task 1", "description A");

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDtoTest);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(taskDtoTest.getId());
    }

    @Test
    public void updateTaskTest () throws Exception {

        //Give
        TaskDto taskDtoTest = new TaskDto(1L,"Test task 1", "description B");
        Task task = new Task(1L,"Test task 2", "description B");

        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(service.saveTask(any())).thenReturn(new Task());
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDtoTest);

        String jsonContent = gson.toJson(taskDtoTest);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask?taskId=1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test task 1")))
                .andExpect(jsonPath("$.content", is("description B")));
    }

    @Test
    public void createTaskTest () throws Exception{

        //Given
        TaskDto taskDto = new TaskDto(1l, "Task", "Test task");

        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(service, times(1)).saveTask(taskMapper.mapToTask(taskDto));
    }
}