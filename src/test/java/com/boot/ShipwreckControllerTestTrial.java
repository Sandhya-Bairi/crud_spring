package com.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTestTrial {

	private static ShipwreckController sc;
	private static Shipwreck a;
	private static Shipwreck b;
	private static Shipwreck c;
	
	@Mock
	private ShipwreckRepository shipwreckRepository; 
	
	@BeforeClass
	public static void setUp() {
		sc = mock(ShipwreckController.class);
		a = new Shipwreck(1L, "U869", "A very deep German UBoat", "FAIR", 200, 44.12, 138.44, 1994);
		b = new Shipwreck(2L, "Thistlegorm", "British merchant boat in the Red Sea", "GOOD", 80, 44.12, 138.44, 1994);
		c = new Shipwreck(3L, "S.S. Yongala", "A luxury passenger ship wrecked on the great barrier reef", "FAIR", 50, 44.12, 138.44, 1994);

		when(sc.list()).thenReturn(Arrays.asList(a,b,c));
		when(sc.create(a)).thenReturn(a);
		when(sc.get(1L)).thenReturn(a);
		when(sc.delete(1L)).thenReturn(null);	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testList() {
		List<Shipwreck> shipwrecks = sc.list();
		Shipwreck myShipwreck = shipwrecks.get(0);
		assertEquals(myShipwreck.getDepth().intValue(),200);
		assertEquals(myShipwreck.getCondition(),"FAIR");
		assertEquals(myShipwreck.getDescription(),"A very deep German UBoat");
		assertEquals(myShipwreck.getId().longValue(),1L);
		assertEquals(myShipwreck.getLatitude().doubleValue(),44.12,0.01);
		assertEquals(myShipwreck.getLongitude().doubleValue(),138.44,0.01);
		assertEquals(myShipwreck.getName(),"U869");
		assertEquals(myShipwreck.getYearDiscovered().intValue(),1994);
	}
	
	@Test
	public void testCreate() {
		//when(sc.create(a)).thenReturn(a);
		Shipwreck sw = sc.create(a);
		assertNotNull(sw);
		assertEquals(sw.getId().intValue(),1L);
		assertSame(a,sw);
	}
	
	@Test
	public void testGet() {
		long id = 1L;
		Shipwreck myShipwreck = sc.get(id);
		assertNotNull(myShipwreck);
		assertEquals(myShipwreck.getDepth().intValue(),200);
		assertEquals(myShipwreck.getCondition(),"FAIR");
		assertEquals(myShipwreck.getDescription(),"A very deep German UBoat");
		assertEquals(myShipwreck.getLatitude().doubleValue(),44.12,0.01);
		assertEquals(myShipwreck.getLongitude().doubleValue(),138.44,0.01);
		assertEquals(myShipwreck.getName(),"U869");
		assertEquals(myShipwreck.getYearDiscovered().intValue(),1994);
	}
	
	@Test
	public void testUpdate() {
		Shipwreck sw = sc.get(1L);
		when(sc.update(1L, sw)).thenReturn(a);
		assertEquals(a.getId().longValue(),1L);
	}
	
	@Test
	public void testDelete() {
		//when(sc.get(1L)).thenReturn(a);
		sc.delete(1L);
		verify(sc).delete(1L);
	}
}
