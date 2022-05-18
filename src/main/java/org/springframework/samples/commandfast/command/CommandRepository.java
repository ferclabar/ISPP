/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.commandfast.command;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface CommandRepository extends CrudRepository<Command, Integer> {

	@Query("SELECT command FROM Command command WHERE command.id =:id")
	public Command findById(@Param("id") int id);

	@Query("SELECT command FROM Command command")
	public List<Command> findCommands();
	
	@Query("SELECT command FROM Command command WHERE restaurante.id =:id AND command.state=FALSE")
	public Collection<Command> findCommandsOfARestaurant(@Param("id") int id);

    
    @Modifying
    @Query("DELETE FROM Command c WHERE c.restaurante.id =:id")
	void deleteCommandById(@Param("id") int id);
    
    
    @Query("SELECT c.id FROM Command c WHERE c.restaurante.id =:id")
	List<Integer> findCommandByRestaurantId(@Param("id") int id);
}
