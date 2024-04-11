package com.example.students.service;

import com.example.students.dto.StudentsRequest;
import com.example.students.dto.StudentsResponse;
import com.example.students.entity.StudentsEntity;



public class StudentsMapping {


    //Phương thức này chuyển đổi từ một đối tượng StudentsRequest (DTO) sang một đối tượng StudentsEntity.
    public static StudentsEntity convertDtotoEntity(StudentsRequest dto)
    {
        //Đầu tiên, nó tạo một đối tượng StudentsEntity mới.
        StudentsEntity entity=new StudentsEntity();

        //Tiếp theo, nó gán các giá trị từ StudentsRequest đã được truyền vào cho các thuộc tính tương ứng của đối tượng StudentsEntity mới tạo.
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setLastname(dto.getLastname());

        //Sau khi hoàn thành, nó trả về đối tượng StudentsEntity đã được thiết lập đầy đủ thông tin.
        return entity;
    }


    //Phương thức này chuyển đổi từ một đối tượng StudentsEntity sang một đối tượng StudentsResponse.
    public static StudentsResponse convertEntityToStudentsResponse(StudentsEntity entity) {
        StudentsResponse dto = new StudentsResponse();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setLastname(entity.getLastname());

        return dto;
    }



}
