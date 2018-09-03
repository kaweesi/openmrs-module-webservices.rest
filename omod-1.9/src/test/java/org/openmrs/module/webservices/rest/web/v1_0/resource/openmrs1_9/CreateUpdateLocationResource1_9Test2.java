/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_9;
 import java.io.InputStream;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.response.ConversionException;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;
 /**
 * Integration tests for the framework that lets a resource handle an entire class hierarchy
 */
public class CreateUpdateLocationResource1_9Test2 extends BaseModuleWebContextSensitiveTest {
	
	private LocationResource1_9 resource;
	
	private static final String LOCATION_TAGS = "loctag_data.xml";
	
	@Before
	public void beforeEachTests() throws Exception {
		executeDataSet(LOCATION_TAGS);
		resource = (LocationResource1_9) Context.getService(RestService.class).getResourceBySupportedClass(Location.class);
	}
	
	@Test(expected = ConversionException.class)
	public void shouldCreateLocation_fromGET() throws Exception {
		SimpleObject locationSimpleObject = new SimpleObject();
		InputStream object = getClass().getClassLoader().getResourceAsStream("create_location_from_get.json");
		Assert.assertNotNull(object);
		locationSimpleObject.putAll(new ObjectMapper().readValue(object, HashMap.class));
		SimpleObject created = (SimpleObject) resource.create(locationSimpleObject, new RequestContext());
	}
	
}