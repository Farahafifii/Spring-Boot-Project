package com.task.recommender;
import com.task.recommender.entity.CourseEntity;

import java.util.*;

public interface CourseRecommender {
    List<CourseEntity> recommendedCourses();
}
