package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Studentmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface Studentrepos extends JpaRepository<Studentmodel,Integer> {

    @Query("select student from Studentmodel student where email=?1 and password=?2")
    Optional<Studentmodel> login(String email,String password);

    @Transactional
    @Modifying
    @Query("update Studentmodel student set student.token=?1 where student.student_id=?2")
    void updateTokenByStudentID(String token,Integer student_id);

    @Query("select student from Studentmodel student where student.student_id=?1 and student.token=?2")
    Optional<Studentmodel> getTokenByStudentId(Integer student_id, String token);

    @Transactional
    @Modifying
    @Query("update Studentmodel student set student.profile_image=?2 where student.student_id=?1")
    void updateProfileImageByStudentId(Integer student_id,String profile_image);

    @Query("select student from Studentmodel student where student.student_id=?1 ")
    Optional<Studentmodel> getRoleByStudentId(Integer student_id);


}
