INSERT INTO public.data_ref
(audit_remark, created_at, created_by, is_activate, "level", order_number, code, parent_code, name_en, name_kh,
 value, description_en, description_kh)
VALUES
    -- Payment Status
    ('', NOW(), 'System', true, 1, 1, 'PAYMENT_STATUS', NULL, 'Payment Status', 'ស្ថានភាពបង់ប្រាក់', NULL, NULL, NULL),
    ('', NOW(), 'System', true, 2, 1, 'PAYMENT_STATUS_UNPAID', 'PAYMENT_STATUS', 'Unpaid', 'មិនទាន់បង់', NULL, NULL,
     NULL),
    ('', NOW(), 'System', true, 2, 2, 'PAYMENT_STATUS_PAID', 'PAYMENT_STATUS', 'Paid', 'បង់រួច', '', NULL, NULL),
    -- Order Status
    ('', NOW(), 'System', true, 1, 1, 'ORDER_STATUS', NULL, 'Order Status', 'ស្ថានភាពបញ្ជាទិញ', '', NULL, NULL),
    ('', NOW(), 'System', true, 2, 1, 'ORDER_STATUS_START', 'ORDER_STATUS', 'Start', 'ចាប់ផ្តើម', NULL, NULL, NULL),
    ('', NOW(), 'System', true, 2, 2, 'ORDER_STATUS_END', 'ORDER_STATUS', 'End', 'រួចរាល់', NULL, NULL, NULL)