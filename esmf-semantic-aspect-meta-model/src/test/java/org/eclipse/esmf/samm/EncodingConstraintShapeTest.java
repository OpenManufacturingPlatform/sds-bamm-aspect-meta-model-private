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

public class EncodingConstraintShapeTest extends AbstractShapeTest {

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testConstraintValidationExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "encoding-constraint-shape", "TestEncodingConstraint", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testInvalidEncodingExpectFailure( final KnownVersion metaModelVersion ) {
      final SammUrns sammUrns = new SammUrns( metaModelVersion );
      final String focusNode = testNamespacePrefix + "TestInvalidEncodingConstraint";

      final SemanticError resultForName = new SemanticError( messageInvalidEncoding,
            focusNode, sammUrns.valueUrn, violationUrn, "UTF-8" );
      expectSemanticValidationErrors( "encoding-constraint-shape", "TestInvalidEncodingConstraint",
            metaModelVersion, resultForName );
   }
}
