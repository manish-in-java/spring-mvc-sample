package org.example.data;

import org.example.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Contract for data access operations on a {@link Model}.
 */
@NoRepositoryBean
public interface ModelRepository<T extends Model> extends JpaRepository<T, Long>
{
}
