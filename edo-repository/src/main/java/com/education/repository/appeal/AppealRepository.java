package com.education.repository.appeal;

import com.education.entity.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {

    /**
     * Метод достает сообщение по id
     */
    Optional<Appeal> findByIdAndArchivedDateIsNull(Long id);


    /**
     * Метод для пагинации
     */
    List<Appeal> findAllByIdOrderByIdAsc(Pageable pageable);


    /**
     * Метод достает Appeal, у которого поле archivedDate = null
     */
    @Query("select u from Appeal u where u.id =:id and u.archivedDate is null ")
    Optional<Appeal> findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод, который достает всех Appeal, у которых поле archivedDate = null
     */
    @Query("select u from Appeal u where u.archivedDate is null")
    Collection<Appeal> findAllNotArchived();

    /**
     * изменяет статус обращения на "на рассмотрении"
     */
    @Modifying
    @Query(nativeQuery = true, value = "update Appeal set appeals_status = 'UNDER_CONSIDERATION' where id =:id and appeals_status = 'NEW_STATUS'")
    void setStatusUnderConsideration(@Param("id") Long id);
}

