package com.task.sumerge.course;

import com.task.sumerge.controller.CourseController;
import com.task.sumerge.dto.CourseDTO;
import com.task.sumerge.exception.BadRequestException;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    public void testAddCourse() throws Exception {
        CourseDTO courseDTO = new CourseDTO("Python Basics", "Introduction to Python", 3);

        mockMvc.perform(post("/courses/add")
                        .contentType("application/json")
                        .content("{\"name\":\"Python Basics\", \"description\":\"Introduction to Python\", \"credit\":3}"))
                .andExpect(status().isOk());

        verify(courseService).addCourse(any(CourseDTO.class));
    }


    @Test
    public void testUpdateCourse() throws Exception {
        CourseDTO courseDTO = new CourseDTO("Advanced Python", "Advanced concepts", 5);
        courseDTO.setId(1);

        mockMvc.perform(put("/courses/update/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Advanced Python\", \"description\":\"Advanced concepts\", \"credit\":5}"))
                .andExpect(status().isOk());

        verify(courseService).updateCourse(any(CourseDTO.class));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        mockMvc.perform(delete("/courses/delete/1"))
                .andExpect(status().isOk());

        verify(courseService).deleteCourse(1);
    }

    @Test
    public void testViewCourse() throws Exception {
        CourseDTO courseDTO = new CourseDTO("Sample Course", "Course Description", 4);
        given(courseService.viewCourse(1)).willReturn(courseDTO);

        mockMvc.perform(get("/courses/view/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Course"))
                .andExpect(jsonPath("$.description").value("Course Description"))
                .andExpect(jsonPath("$.credit").value(4));

        verify(courseService).viewCourse(1);
    }

    @Test
    public void testDiscoverCourses() throws Exception {
        mockMvc.perform(get("/courses/discover"))
                .andExpect(status().isOk());

        verify(courseService).discoverCourses("Python");
    }

    @Test
    public void testFindAllCourses() throws Exception {
        mockMvc.perform(get("/courses/findAll")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        verify(courseService).findAllCourses(0, 10);
    }

    @Test
    public void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/courses/getAll"))
                .andExpect(status().isOk());

        verify(courseService).getAllCourses();
    }

    @Test
    public void testUpdateNonExistingCourse() throws Exception {
        given(courseService.updateCourse(any(CourseDTO.class))).willThrow(new ResourceNotFoundException("Course does not exist"));

        mockMvc.perform(put("/courses/update/99")
                        .contentType("application/json")
                        .content("{\"name\":\"Non-Existing Course\", \"description\":\"\", \"credit\":0}"))
                .andExpect(status().isNotFound());

        verify(courseService).updateCourse(any(CourseDTO.class));
    }

    @Test
    public void testViewNonExistingCourse() throws Exception {
        given(courseService.viewCourse(10000000)).willThrow(new ResourceNotFoundException("Course not found for this id :: 10000000"));

        mockMvc.perform(get("/courses/view/10000000"))
                .andExpect(status().isNotFound());

        verify(courseService).viewCourse(10000000);
    }

    @Test
    public void testUpdateCourseInvalidData() throws Exception {
        String invalidCourseJson = "{\"name\":\"\", \"description\":\"\", \"credit\":-1, \"id\":1}";

        doThrow(new BadRequestException("Invalid course data"))
                .when(courseService).updateCourse(any(CourseDTO.class));

        mockMvc.perform(put("/courses/update/1")
                        .contentType("application/json")
                        .content(invalidCourseJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddCourseInvalidData() throws Exception {
        String invalidCourseJson = "{\"name\":\"\", \"description\":\"\", \"credit\":-1}";

        doThrow(new BadRequestException("Invalid course data"))
                .when(courseService).addCourse(any(CourseDTO.class));

        mockMvc.perform(post("/courses/add")
                        .contentType("application/json")
                        .content(invalidCourseJson))
                .andExpect(status().isBadRequest());
    }

}
