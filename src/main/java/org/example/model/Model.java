package org.example.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

/**
 * Represents a domain entity.
 */
@MappedSuperclass
public abstract class Model
{
  @Column(name = "id")
  @Generated(GenerationTime.INSERT)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  /**
   * Deliberately hidden to prevent direct instantiation.
   */
  protected Model()
  {
    super();
  }

  /**
   * Gets the unique identifier for this entity instance.
   *
   * @return The unique identifier for this entity instance.
   */
  public Long getID()
  {
    return this.id;
  }
}
