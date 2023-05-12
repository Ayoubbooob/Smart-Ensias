package com.ensias.ensiasattendease.services.implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensias.ensiasattendease.models.CourseModel;
import com.ensias.ensiasattendease.models.CoursePlanningModel;
import com.ensias.ensiasattendease.models.DaysOfWeek;
import com.ensias.ensiasattendease.models.FiliereModel;
import com.ensias.ensiasattendease.models.PlanningModel;
import com.ensias.ensiasattendease.repositories.ClasseRepository;
import com.ensias.ensiasattendease.repositories.CoursePlanningRepository;
import com.ensias.ensiasattendease.repositories.CourseRepository;
import com.ensias.ensiasattendease.repositories.FiliereRepository;
import com.ensias.ensiasattendease.repositories.PlanningRepository;
import com.ensias.ensiasattendease.services.FiliereService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private CourseRepository courseRepository ;

    @Autowired 
    ClasseRepository classeRepository ;

    @Autowired
    private  CoursePlanningRepository coursePlanningRepository ;

    @Autowired
    private PlanningRepository planningRepository ;

    public FiliereServiceImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public List<FiliereModel> getAllFiliere() {
        return filiereRepository.findAll();
    }

    @Override
    public Collection<CourseModel> getAllFiliereCourse(Long filierId) {
       if(filiereRepository.findById(filierId).orElse(null) == null){
            return null ; 
       }
        return filiereRepository.findById(filierId).get().getCourse() ;
    }

    @Override
    public CourseModel addCourseToFilier(Long filierId, CourseModel course) {
        FiliereModel filiere = filiereRepository.findById(filierId).get() ; 
        if(filiere == null){
            return null ; 
        }
        filiere.getCourse().add(course) ; 
        course.getFiliere().add(filiere);
        filiereRepository.save(filiere) ;
        
        return courseRepository.save(course)  ;
    }

    @Override
    public FiliereModel addFilier(FiliereModel filiere) {
        try {
            return filiereRepository.save(filiere);
        } catch (Exception e) {
            // TODO: handle exception
            return null ;
        }
    }

    @Override
    public PlanningModel addPlanningToFilier(JsonNode node, Long filierId) {
        try {
            PlanningModel newPlan = new PlanningModel();
            JsonNode startedDateNode =  node.get("startedDate") ;
            JsonNode endedDateNode =  node.get("endedDate") ;
            JsonNode nowdateNode = node.get("nowDate") ;
            JsonNode coursePlanningNode =  node.get("coursePlanning") ;
            ObjectMapper startedDateNodeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
            ObjectMapper endedDateNodeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
            ObjectMapper nowdateMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
            LocalDate startedDate = startedDateNodeMapper.readValue(startedDateNode.toString(), LocalDate.class);
            LocalDate endedDate = endedDateNodeMapper.readValue(endedDateNode.toString(), LocalDate.class);
            LocalDate nowDate = nowdateMapper.readValue(nowdateNode.toString(), LocalDate.class);
            newPlan.setStartedDate(startedDate);
            newPlan.setEndedDate(endedDate);
            newPlan.setNowDate(nowDate);
            coursePlanningNode.forEach(value -> {
                try {
                    CoursePlanningModel newCoursePlan = new CoursePlanningModel() ;
                    JsonNode startedTimeNode = value.get("startedTime") ;
                    JsonNode endedTimeNode = value.get("endedTime") ;
                    JsonNode dayNode = value.get("day") ;
                    JsonNode classIdNode = value.get("classe_id") ;
                    JsonNode courseIdNode = value.get("course_id") ;
                    ObjectMapper startedTimeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
                    ObjectMapper endedTimeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
                    ObjectMapper dayMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
                    ObjectMapper classIdNodeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
                    ObjectMapper courseIdNodeMapper = new ObjectMapper().registerModule(new JavaTimeModule()) ;
                    LocalTime startedTimeDate = startedTimeMapper.readValue(startedTimeNode.toString(), LocalTime.class);
                    LocalTime endedTimeDate = endedTimeMapper.readValue(endedTimeNode.toString(), LocalTime.class);
                    Long classId = classIdNodeMapper.readValue(classIdNode.toString(), Long.class);
                    Long courseId = courseIdNodeMapper.readValue(courseIdNode.toString(), Long.class);
                    DaysOfWeek dayDate = dayMapper.readValue(dayNode.toString(), DaysOfWeek.class);
                    newCoursePlan.setStartedTime(startedTimeDate);
                    newCoursePlan.setEndedTime(endedTimeDate);
                    newCoursePlan.setDay(dayDate);
                    newCoursePlan.setClasse(classeRepository.findById(classId).orElse(null));
                    newCoursePlan.setCourses(courseRepository.findById(courseId).orElse(null));
                    coursePlanningRepository.save(newCoursePlan) ; 
                    newPlan.getCoursePlanning().add(newCoursePlan) ;
                } catch (Exception e) {
                    // TODO: handle exception
                    throw new RuntimeException(e) ;
                }
            });
            FiliereModel filiere = filiereRepository.findById(filierId).orElse(null) ;
            PlanningModel planningModel = planningRepository.findAll().stream().filter(plan -> plan.getFiliere().stream().filter(fil ->fil.getId().equals(filierId)).count() > 0).findFirst().orElse(null);
            if(filiere == null){
                return null ; 
            }
            if(planningModel != null){
                newPlan.setId(planningModel.getId());
                filiere.setPlanning(newPlan);
                filiereRepository.save(filiere) ;
                return  planningRepository.save(newPlan);
            }
            newPlan.getFiliere().add(filiere);
            filiere.setPlanning(newPlan);
            filiereRepository.save(filiere) ;
            return planningRepository.save(newPlan);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e) ;
        }
    }

    @Override
    public Boolean deleteFiliere(Long FiliereId) {
        try {
            FiliereModel filiere = filiereRepository.findById(FiliereId).orElse(null) ; 
            if(filiere == null ){
                return false ; 
            }
            filiereRepository.deleteById(filiere.getId());
            return true ;
        } catch (Exception e) {
            // TODO: handle exception
            return false ; 
        } 

    }

    @Override
    public PlanningModel deserilizeAddPlanningToFilierBody(JsonNode node) {
        try {
            JsonNode startedDateNode =  node.get("startedDate") ;
            JsonNode endedDateNode =  node.get("endedDate") ;
            JsonNode nowdate = node.get("nowdate") ;
            JsonNode coursePlanningNode =  node.get("coursePlanning") ;
            ObjectMapper startedDateNodeMapper = new ObjectMapper() ;
            ObjectMapper endedDateNodeMapper = new ObjectMapper() ;
            ObjectMapper nowdateMapper = new ObjectMapper() ;
            ObjectMapper coursePlanningNodeMapper = new ObjectMapper() ;
            LocalDate startedDate = startedDateNodeMapper.readValue(startedDateNode.toString(), LocalDate.class);
            LocalDate endedDate = endedDateNodeMapper.readValue(endedDateNode.toString(), LocalDate.class);
            LocalDate nowDate = nowdateMapper.readValue(nowdate.toString(), LocalDate.class);
            coursePlanningNode.forEach(value -> {

            });
        } catch (Exception e) {
            return null ;
        }


        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserilizeAddPlanningToFilierBody'");
    }

    
    
}
