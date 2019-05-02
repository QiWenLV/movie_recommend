package com.zqw.movie_recommend.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class KV<K, V> {
    K k;
    V v;
}
