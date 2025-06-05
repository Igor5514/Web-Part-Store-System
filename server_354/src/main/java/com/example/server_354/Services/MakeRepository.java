package com.example.server_354.Services;

import com.example.server_354.object.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MakeRepository extends JpaRepository<Make, Long> {

    @Query(value = "SELECT make_id from make WHERE make = :make", nativeQuery = true)
    Integer getMakeIdByMake(@Param("make") String make);

    @Query(value = "SELECT exists(select 1 from make where make.make = :make)", nativeQuery = true)
    Long checkIfMakeExist(@Param("make") String make);

}
