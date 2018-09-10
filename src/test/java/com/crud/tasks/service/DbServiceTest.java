package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Before
    public void baseToTests () {
        List<Task> taskList = Arrays.asList(new Task(1L,"test", "description A"),
                new Task(2L, "test", "description B"));
        when(repository.findAll()).thenReturn(taskList);
    }

    @Test
    public void getAllTasksTest () {

        //Given
        List<Task> taskList = Arrays.asList(new Task(1L,"test", "description A"),
                new Task(2L, "test", "description B"));
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> listResult = dbService.getAllTasks();

        //Then
        Assert.assertEquals(2, listResult.size());
    }

    @Test
    public void getTaskTest () {

        //Given
        List<Task> taskList = Arrays.asList(new Task(1L,"test", "description A"),
                new Task(2L, "test", "description B"));
        when(repository.findAll()).thenReturn(taskList);
        List<Task> listResult = dbService.getAllTasks();
        when(repository.findById(listResult.get(0).getId())).thenReturn(Optional.of(listResult.get(0)));
        //When
        Optional<Task> taskresult = dbService.getTask(1L);

        //Then
        Assert.assertEquals("test", taskresult.get().getTitle());
        Assert.assertEquals("description A", taskresult.get().getContent());
    }

    @Test
    public void saveTaskTest (){

        //Given
        Task newTask = new Task(1L,"test", "description A");
        when(repository.save(newTask)).thenReturn(newTask);

        //When
        Task taskresult = dbService.saveTask(newTask);

        //Then
        Assert.assertEquals("test", taskresult.getTitle());
        Assert.assertEquals("description A", taskresult.getContent());
    }

    @Test
    public void deleteTaskTest () {

        //Given
        Long id = 2L;
        //When
        dbService.deleteTask(id);
        //Then
        verify(repository, times(1)).delete(id);
    }


}
