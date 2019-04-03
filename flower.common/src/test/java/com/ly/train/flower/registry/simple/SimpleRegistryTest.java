/**
 * Copyright © 2019 同程艺龙 (zhihui.li@ly.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package com.ly.train.flower.registry.simple;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import com.ly.train.flower.base.service.ServiceA;
import com.ly.train.flower.common.util.URL;
import com.ly.train.flower.registry.Registry;
import com.ly.train.flower.registry.RegistryFactory;
import com.ly.train.flower.registry.ServiceInfo;

/**
 * @author leeyazhou
 *
 */
public class SimpleRegistryTest {

  @Test
  public void testRegister() throws Exception {

    URL url = new URL("http", "127.0.0.1", 8080);
    RegistryFactory factory = new SimpleRegistryFactory();
    Registry registry = factory.createRegistry(url);


    ServiceInfo serviceInfo = new ServiceInfo();
    serviceInfo.setApplication("commonservice");
    serviceInfo.setClassName(ServiceA.class.getName());
    Set<String> host = new HashSet<>();
    host.add("127.0.0.1:12001");
    host.add("127.0.0.1:12002");
    serviceInfo.setHost(host);
    serviceInfo.setCreateTime(new Date());
    registry.register(serviceInfo);

  }

  @Test
  public void testGetProviders() throws Exception {
    URL url = new URL("http", "127.0.0.1", 8080);
    RegistryFactory factory = new SimpleRegistryFactory();
    Registry registry = factory.createRegistry(url);


    ServiceInfo serviceInfo = new ServiceInfo();
    serviceInfo.setClassName(ServiceA.class.getName());
    Set<String> host = new HashSet<>();
    host.add("127.0.0.1:12001");
    host.add("127.0.0.1:12002");
    serviceInfo.setHost(host);
    serviceInfo.setCreateTime(new Date());
    List<ServiceInfo> serviceInfos = registry.getProvider(serviceInfo);
    System.out.println("请求结果:" + serviceInfos);
  }
}
