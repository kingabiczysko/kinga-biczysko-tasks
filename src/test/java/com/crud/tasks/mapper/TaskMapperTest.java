package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();
    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto((long) 1,"task1", "Modul 21.1.");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals("Modul 21.1.", mappedTask.getContent());


    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task((long) 2,"task2", "Modul 21.2.");

        //When

        TaskDto mappedTask = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals("task2", mappedTask.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task((long) 3, "task3", "Modul 21.3."));
        taskList.add(new Task((long) 4, "task4", "Modul 21.4."));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals("Modul 21.3.", taskDtoList.get(0).getContent());
        Assert.assertEquals("task4", taskDtoList.get(1).getTitle());
    }
}