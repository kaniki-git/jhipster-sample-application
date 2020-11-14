package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabProfilTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabProfil.class);
        TabProfil tabProfil1 = new TabProfil();
        tabProfil1.setId(1L);
        TabProfil tabProfil2 = new TabProfil();
        tabProfil2.setId(tabProfil1.getId());
        assertThat(tabProfil1).isEqualTo(tabProfil2);
        tabProfil2.setId(2L);
        assertThat(tabProfil1).isNotEqualTo(tabProfil2);
        tabProfil1.setId(null);
        assertThat(tabProfil1).isNotEqualTo(tabProfil2);
    }
}
