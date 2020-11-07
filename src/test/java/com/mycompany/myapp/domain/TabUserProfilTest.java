package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabUserProfilTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabUserProfil.class);
        TabUserProfil tabUserProfil1 = new TabUserProfil();
        tabUserProfil1.setId(1L);
        TabUserProfil tabUserProfil2 = new TabUserProfil();
        tabUserProfil2.setId(tabUserProfil1.getId());
        assertThat(tabUserProfil1).isEqualTo(tabUserProfil2);
        tabUserProfil2.setId(2L);
        assertThat(tabUserProfil1).isNotEqualTo(tabUserProfil2);
        tabUserProfil1.setId(null);
        assertThat(tabUserProfil1).isNotEqualTo(tabUserProfil2);
    }
}
