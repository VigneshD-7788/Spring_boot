package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Book_historymodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepos extends JpaRepository<Book_historymodel,Integer> {
}
