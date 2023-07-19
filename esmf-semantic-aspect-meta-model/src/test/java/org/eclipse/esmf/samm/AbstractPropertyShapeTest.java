/*
 * Copyright (c) 2023 Robert Bosch Manufacturing Solutions GmbH
 *
 * See the AUTHORS file(s) distributed with this work for additional
 * information regarding authorship.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.eclipse.esmf.samm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.eclipse.esmf.samm.validation.SemanticError;

public class AbstractPropertyShapeTest extends AbstractShapeTest {
   private final String focusNode = testNamespacePrefix + "testProperty";

   @ParameterizedTest
   @MethodSource( value = "versionsStartingWith2_0_0" )
   public void testAbstractPropertyValidationExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "abstract-property-shape", "TestAbstractProperty", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "versionsStartingWith2_0_0" )
   public void testAbstractPropertyWithCharacteristicExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final SemanticError resultForName = new SemanticError( messageMoreThanZeroValues,
            testNamespacePrefix + "x", sammUrns.characteristicUrn, violationUrn, "" );
      expectSemanticValidationErrors( "abstract-property-shape", "TestAbstractPropertyWithCharacteristic",
            metaModelVersion, resultForName );
   }

   @ParameterizedTest
   @MethodSource( value = "versionsStartingWith2_0_0" )
   public void testAbstractPropertyWithExampleValueExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );

      final SemanticError resultForName = new SemanticError( messageMoreThanZeroValues,
            testNamespacePrefix + "x", sammUrns.exampleValueUrn, violationUrn, "" );
      expectSemanticValidationErrors( "abstract-property-shape", "TestAbstractPropertyWithExampleValue",
            metaModelVersion, resultForName );
   }
}
