package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabSpecialiteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabSpecialite.class);
        TabSpecialite tabSpecialite1 = new TabSpecialite();
        tabSpecialite1.setId(1L);
        TabSpecialite tabSpecialite2 = new TabSpecialite();
        tabSpecialite2.setId(tabSpecialite1.getId());
        assertThat(tabSpecialite1).isEqualTo(tabSpecialite2);
        tabSpecialite2.setId(2L);
        assertThat(tabSpecialite1).isNotEqualTo(tabSpecialite2);
        tabSpecialite1.setId(null);
        assertThat(tabSpecialite1).isNotEqualTo(tabSpecialite2);
    }
}
