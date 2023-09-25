package com.example.shelter_bot.repository;

import com.example.shelter_bot.model.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Interface CatRepository.
 * @author Lubava
 * @version 1.0.0
 */

public interface ReportDataRepository extends JpaRepository<ReportData, Long> {

    Set<ReportData> findListByChatId(Long id);

    ReportData findByChatId(Long id);

}

