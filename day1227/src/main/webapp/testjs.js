var xhr = new XMLHttpRequest();
var url = 'http://openapi.q-net.or.kr/api/service/rest/InquiryListNationalQualifcationSVC/getList'; /*URL*/
var queryParams1 = '?' + decodeURIComponent('serviceKey') + '='+'mjJMdl1IEJmtDxQ+5j7WOGf40HXzwOlqkJ971ptzR7AndA9ONtQgYvugFAk19JKPRW2h/Z4Oe46sN8F6KcX9PA=='; /*Service Key*/
var queryParams2 = '?' + encodeURIComponent('serviceKey') + '='+'mjJMdl1IEJmtDxQ%2B5j7WOGf40HXzwOlqkJ971ptzR7AndA9ONtQgYvugFAk19JKPRW2h%2FZ4Oe46sN8F6KcX9PA%3D%3D'; /*Service Key*/
xhr.open('GET', url + queryParams1+queryParams2);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
    }
};

xhr.send('');