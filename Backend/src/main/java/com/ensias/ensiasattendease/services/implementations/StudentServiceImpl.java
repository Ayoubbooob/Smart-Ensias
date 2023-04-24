package com.ensias.ensiasattendease.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.StudentModel;
import com.ensias.ensiasattendease.repositories.StudentRepository;
import com.ensias.ensiasattendease.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository ;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository ; 
    }

    @Override
    public List<StudentModel> getAllStudent(){
        return studentRepository.findAll();
    }
    
}
