package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabAccouchementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabAccouchement.class);
        TabAccouchement tabAccouchement1 = new TabAccouchement();
        tabAccouchement1.setId(1L);
        TabAccouchement tabAccouchement2 = new TabAccouchement();
        tabAccouchement2.setId(tabAccouchement1.getId());
        assertThat(tabAccouchement1).isEqualTo(tabAccouchement2);
        tabAccouchement2.setId(2L);
        assertThat(tabAccouchement1).isNotEqualTo(tabAccouchement2);
        tabAccouchement1.setId(null);
        assertThat(tabAccouchement1).isNotEqualTo(tabAccouchement2);
    }
}
