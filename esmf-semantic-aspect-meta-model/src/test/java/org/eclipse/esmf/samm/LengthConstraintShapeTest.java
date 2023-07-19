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

import org.apache.jena.vocabulary.XSD;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.eclipse.esmf.samm.validation.SemanticError;

public class LengthConstraintShapeTest extends AbstractShapeTest {

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "length-constraint-shape", "TestLengthConstraint", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithOnlyMinValueExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "length-constraint-shape", "TestLengthConstraintWithOnlyMinValue", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithOnlyMaxValueExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "length-constraint-shape", "TestLengthConstraintWithOnlyMaxValue", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithDateTimeExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "length-constraint-shape", "TestLengthConstraintWithDateTime", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithCollectionExpectSuccess( final KnownVersion metaModelVersion ) {
      checkValidity( "length-constraint-shape", "TestLengthConstraintWithCollection", metaModelVersion );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithInvalidTypeExpectFailure( final KnownVersion metaModelVersion ) {
      final String focusNode = testNamespacePrefix + "TestLengthConstraintWithInvalidType";
      final SemanticError resultForDataType = new SemanticError(
            validator.getMessageText( "samm-c:LengthConstraintShape", "ERR_WRONG_DATATYPE", metaModelVersion ),
            focusNode, "", violationUrn, XSD.xboolean.getURI() );
      expectSemanticValidationErrors( "length-constraint-shape", "TestLengthConstraintWithInvalidType",
            metaModelVersion, resultForDataType );
   }

   @ParameterizedTest
   @MethodSource( value = "allVersions" )
   public void testLengthConstraintValidationWithInvalidMinMaxExpectFailure( final KnownVersion metaModelVersion ) {
      final String focusNode = testNamespacePrefix + "TestLengthConstraintWithInvalidMinMax";
      final SemanticError resultForDataType = new SemanticError(
            validator.getMessageText( "samm-c:LengthConstraintShape", "ERR_INVALID_VALUE", metaModelVersion ),
            focusNode, "", violationUrn, "minValue: 2 maxValue: 1" );
      expectSemanticValidationErrors( "length-constraint-shape", "TestLengthConstraintWithInvalidMinMax",
            metaModelVersion, resultForDataType );
   }
}
