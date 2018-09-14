package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTaskTest (){

        //Given
        TaskDto taskDto = new TaskDto(2L,"Test", "content A");

        Task taskToCompare = new Task(2L,"Test", "content A");

        //When
        Task taskResult = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(taskToCompare, taskResult);
    }

    @Test
    public void mapToTaskDtoTest (){

        //Given
        Task task = new Task(2L,"Test", "content A");

        TaskDto taskDtoToCompare = new TaskDto(2L,"Test", "content A");

        //When
        TaskDto taskResult = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(taskDtoToCompare, taskResult);
    }

    @Test
    public void mapToTaskDtoListTest () {

        //Given
        List<Task> taskList = Arrays.asList(new Task(1L,"Test 2", "description A"),
                new Task(2L,"Test 3","description B"));

        List<TaskDto> listToCompare = Arrays.asList(new TaskDto(1L,"Test 2", "description A"),
                new TaskDto(2L,"Test 3","description B"));

        //When
        List<TaskDto> listResult = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(listToCompare, listResult);
    }
}
