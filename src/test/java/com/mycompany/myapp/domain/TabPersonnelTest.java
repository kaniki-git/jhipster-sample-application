package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabPersonnelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabPersonnel.class);
        TabPersonnel tabPersonnel1 = new TabPersonnel();
        tabPersonnel1.setId(1L);
        TabPersonnel tabPersonnel2 = new TabPersonnel();
        tabPersonnel2.setId(tabPersonnel1.getId());
        assertThat(tabPersonnel1).isEqualTo(tabPersonnel2);
        tabPersonnel2.setId(2L);
        assertThat(tabPersonnel1).isNotEqualTo(tabPersonnel2);
        tabPersonnel1.setId(null);
        assertThat(tabPersonnel1).isNotEqualTo(tabPersonnel2);
    }
}
