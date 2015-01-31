/*
 * * Copyright 2015 Swar Shah <swar12894@gmail.com>.
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

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author  Swar Shah <swar12894@gmail.com>
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
    @Test
    public void testNewOrderWhenCustomerDoesNotExistExceptionThrown(){
        boolean didThrow = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("","");
        order.addPurchase(new Purchase("PROD0001", 100));
        order.addPurchase(new Purchase("PROD0002", 150));
        try{
            orderQueue.add(order);
        } catch (Exception ex){
            didThrow = true;
        }        
        assertTrue(didThrow);
    }
    
    @Test
    public void testNewOrderWhenNoPurchases(){
        boolean didThrow = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        try{
            orderQueue.add(order);
        } catch (Exception ex){
            didThrow = true;
        }        
        assertTrue(didThrow);
    }
    
    @Test
    public void testReturnOrderWithEarliestTimeReceivedThatNotHaveTimeProcessed(){
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        orderQueue.add(order);
        Order result = orderQueue.requestNextOrder();
        Order expResult = orderQueue.orderQueue.peek();
        assertEquals(result, expResult);
    }
    
    @Test
    public void testReturnNullWhenThereAreNoOrders(){
        OrderQueue orderQueue = new OrderQueue();
        Order result = orderQueue.requestNextOrder();
        assertEquals(result, null);
    }
    
    @Test
    public void testSetTimeProcessedToNowWhenHasTimeReceived(){
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase("PROD0004", 450));
        order.addPurchase(new Purchase("PROD0006", 250));
        order.setTimeReceived(null);
        
    }
}
