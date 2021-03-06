/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.dom.simple;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import domainapp.dom.tipodocumento.TipoDoc;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Empleado.class
)
@DomainServiceLayout(
        named = "Menu de Servicios",//
        menuOrder = "10"
)
public class EmpleadoMenu {


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Empleado> listAll() {
        return simpleObjectRepository.listAll();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Empleado> findByName(
            @ParameterLayout(named="Name")
            final String name
    ) {
        return simpleObjectRepository.findByName(name);
    }
    
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    public List<Empleado> buscarPorRol(
            @ParameterLayout(named="Rol")
            final String rol
    ) {
        return simpleObjectRepository.buscarPorRol(rol);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<EmpleadoMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "4")
    public Empleado create(
            @ParameterLayout(named="Name")final String name,
            @ParameterLayout(named="Apellido")final String apellido,
            @ParameterLayout(named="Documento")final String documento,
            @ParameterLayout(named="Tipo Documento") final TipoDoc tipo,
            @ParameterLayout(named="Cuil") final String cuil,
            @ParameterLayout(named="Direccion") final String direccion,
            @ParameterLayout(named="Rol") final String rol){
        return simpleObjectRepository.create(name, apellido,documento, tipo, cuil, direccion, rol);
    }


    @javax.inject.Inject
    EmpleadoRepository simpleObjectRepository;

}
