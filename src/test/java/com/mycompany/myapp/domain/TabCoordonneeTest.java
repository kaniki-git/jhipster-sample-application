package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabCoordonneeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabCoordonnee.class);
        TabCoordonnee tabCoordonnee1 = new TabCoordonnee();
        tabCoordonnee1.setId(1L);
        TabCoordonnee tabCoordonnee2 = new TabCoordonnee();
        tabCoordonnee2.setId(tabCoordonnee1.getId());
        assertThat(tabCoordonnee1).isEqualTo(tabCoordonnee2);
        tabCoordonnee2.setId(2L);
        assertThat(tabCoordonnee1).isNotEqualTo(tabCoordonnee2);
        tabCoordonnee1.setId(null);
        assertThat(tabCoordonnee1).isNotEqualTo(tabCoordonnee2);
    }
}
