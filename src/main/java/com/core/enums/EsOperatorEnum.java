package com.core.enums;

/**
 * SQL  运算符语法枚举
 */
public enum EsOperatorEnum {
    MATCH_ALL("matchAllQuery"),
    SPAN_WITHIN("spanWithinQuery"),
    COMMON_TERMS("commonTermsQuery"),
    SPAN_OR("spanOrQuery"),
    MULTI_MATCH("multiMatchQuery"),
    MATCH_PHRASE("matchPhraseQuery"),
    RANGE("rangeQuery"),
    SPAN_FIRST("spanFirstQuery"),
    SCRIPT_SCORE("scriptScoreQuery"),
    DIS_MAX("disMaxQuery"),
    MATCH("matchQuery"),
    REGEXP("regexpQuery"),
    IDS("idsQuery"),
    TERM("termQuery"),
    PREFIX("prefixQuery"),
    WILDCARD("wildcardQuery"),
    BOOSTING("boostingQuery"),
    FUZZY("fuzzyQuery"),
    BOOL("boolQuery"),
    SPAN_NEAR("spanNearQuery"),
    QUERY_STRING("queryStringQuery"),
    SPAN_TERM("spanTermQuery"),
    SPAN_NOT("spanNotQuery"),
    TERMS("termsQuery"),
    SCRIPT("scriptQuery"),
    WRAPPER("wrapperQuery"),
    TERMS_LOOKUP("termsLookupQuery"),
    EXISTS("existsQuery"),
    GEO_DISJOINT("geoDisjointQuery"),
    TYPE("typeQuery"),
    GEO_WITHIN("geoWithinQuery"),
    GEO_DISTANCE("geoDistanceQuery"),
    GEO_POLYGON("geoPolygonQuery"),
    NESTED("nestedQuery"),
    GEO_SHAPE("geoShapeQuery"),
    DISTANCE_FEATURE("distanceFeatureQuery"),
    SIMPLE_STRING("simpleQueryStringQuery"),
    SPAN_MULTI_TERM_BUILDER("spanMultiTermQueryBuilder"),
    GEO_BOUNDING_BOX("geoBoundingBoxQuery"),
    GEO_INTERSECTION("geoIntersectionQuery"),
    MATCH_PHRASE_PREFIX("matchPhrasePrefixQuery"),
    CONSTANT_SCORE("constantScoreQuery"),
    FUNCTION_SCORE("functionScoreQuery"),
    SPAN_CONTAINING("spanContainingQuery"),
    MORE_LIKE_THIS("moreLikeThisQuery"),
    FIELD_MASKING_SPAN("fieldMaskingSpanQuery");

    private String methodName;

    EsOperatorEnum(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
    }
