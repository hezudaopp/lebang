package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jawinton on 17/05/02.
 */
@Repository
public interface AppRepository extends JpaRepository<App, Long> {
    List<App> findByEnabled(boolean enabled);
    App findFirstByName(String name);
}
