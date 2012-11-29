// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package si.kcclass.newslettersender.domain;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import si.kcclass.newslettersender.domain.SecurityRole;
import si.kcclass.newslettersender.domain.SecurityRoleDataOnDemand;
import si.kcclass.newslettersender.domain.SecurityRoleIntegrationTest;

privileged aspect SecurityRoleIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SecurityRoleIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SecurityRoleIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: SecurityRoleIntegrationTest: @Transactional;
    
    @Autowired
    private SecurityRoleDataOnDemand SecurityRoleIntegrationTest.dod;
    
    @Test
    public void SecurityRoleIntegrationTest.testCountSecurityRoles() {
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", dod.getRandomSecurityRole());
        long count = SecurityRole.countSecurityRoles();
        Assert.assertTrue("Counter for 'SecurityRole' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testFindSecurityRole() {
        SecurityRole obj = dod.getRandomSecurityRole();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to provide an identifier", id);
        obj = SecurityRole.findSecurityRole(id);
        Assert.assertNotNull("Find method for 'SecurityRole' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SecurityRole' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testFindAllSecurityRoles() {
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", dod.getRandomSecurityRole());
        long count = SecurityRole.countSecurityRoles();
        Assert.assertTrue("Too expensive to perform a find all test for 'SecurityRole', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SecurityRole> result = SecurityRole.findAllSecurityRoles();
        Assert.assertNotNull("Find all method for 'SecurityRole' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SecurityRole' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testFindSecurityRoleEntries() {
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", dod.getRandomSecurityRole());
        long count = SecurityRole.countSecurityRoles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SecurityRole> result = SecurityRole.findSecurityRoleEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SecurityRole' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SecurityRole' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testFlush() {
        SecurityRole obj = dod.getRandomSecurityRole();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to provide an identifier", id);
        obj = SecurityRole.findSecurityRole(id);
        Assert.assertNotNull("Find method for 'SecurityRole' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySecurityRole(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'SecurityRole' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testMergeUpdate() {
        SecurityRole obj = dod.getRandomSecurityRole();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to provide an identifier", id);
        obj = SecurityRole.findSecurityRole(id);
        boolean modified =  dod.modifySecurityRole(obj);
        Integer currentVersion = obj.getVersion();
        SecurityRole merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SecurityRole' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", dod.getRandomSecurityRole());
        SecurityRole obj = dod.getNewTransientSecurityRole(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SecurityRole' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'SecurityRole' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void SecurityRoleIntegrationTest.testRemove() {
        SecurityRole obj = dod.getRandomSecurityRole();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SecurityRole' failed to provide an identifier", id);
        obj = SecurityRole.findSecurityRole(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'SecurityRole' with identifier '" + id + "'", SecurityRole.findSecurityRole(id));
    }
    
}
