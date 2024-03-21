package com.scaler.splitwise.Repositories;

import com.scaler.splitwise.Models.Expense;
import com.scaler.splitwise.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group , Long > {
    Optional<Group> findbyId(Long Id);
}
