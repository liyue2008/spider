/**
 * Created by liyue on 2017/5/15.
 */
if (true === true) {
    var ue_t0 = (+new Date()),
        ue_csm = window,
        ue = {
            t0: ue_t0, d: function () {
                return (+new Date() - ue_t0);
            }
        },
        ue_furl = "fls-cn.amazon.cn",
        ue_mid = "AAHKV2X7AFYLW",
        ue_sid = (document.cookie.match(/session-id=([0-9-]+)/) || [])[1],
        ue_sn = "opfcaptcha.amazon.cn",
        ue_id = 'C0BHBBSRVAFH6TGWY56R';
}

if (true === true) {
    var elem = document.createElement("script");
    elem.src = "https://images-cn.ssl-images-amazon.com/images/G/01/csminstrumentation/csm-captcha-instrumentation.min._V" + (+new Date()) + "_.js";
    document.getElementsByTagName('head')[0].appendChild(elem);
}


var ue_modules = ["latency"], cel_widgets = [{c: "celwidget"}], ue_cel_ns = "opfcsm-i", ue_skc = 0, ue_err = {
    startTimer: function () {
    }
};
window.location && window.location.href && -1 != window.location.href.indexOf("//www.amazon.fr") && (ue_mid = "A13V1IB3VIYZZH");
(function (c) {
    var a = c.ue = c.ue || {}, b = Date.now || function () {
            return +new Date
        };
    a.d = function (a) {
        return b() - (a ? 0 : c.ue_t0)
    };
    a.stub = function (b, d) {
        if (!b[d]) {
            var e = [];
            b[d] = function () {
                e.push([e.slice.call(arguments), a.d(), c.ue_id])
            };
            b[d].replay = function (a) {
                for (var b; b = e.shift();)a(b[0], b[1], b[2])
            };
            b[d].isStub = 1
        }
    }
})(ue_csm);
ue.stub(ue, "log");
ue.stub(ue, "onunload");
ue.stub(ue, "onflush");
(function (c) {
    var a = c.ue;
    a.cv = {};
    a.cv.scopes = {};
    a.count = function (b, c, d) {
        var e = {}, f = a.cv;
        e.counter = b;
        e.value = c;
        e.t = a.d();
        d && d.scope && (f = a.cv.scopes[d.scope] = a.cv.scopes[d.scope] || {}, e.scope = d.scope);
        if (void 0 === c)return f[b];
        f[b] = c;
        b = 0;
        d && d.bf && (b = 1);
        a.clog && 0 === b ? a.clog(e, "csmcount", {bf: b}) : a.log && a.log(e, "csmcount", {c: 1, bf: b})
    };
    a.count("baselineCounter2", 1)
})(ue_csm);
(function (c) {
    function a(a) {
        var c = document.createElement("script");
        c.src = a;
        document.getElementsByTagName("head")[0].appendChild(c);
        return c
    }

    a("https://images-na.ssl-images-amazon.com/images/G/01/csminstrumentation/ue-base-1c399ad9886cab69575e1e5ee15c61a1._V313498596_.js").onload = function () {
        c.ue_sid = c.ue_sid || ue.sid;
        a("https://images-na.ssl-images-amazon.com/images/G/01/AUIClients/" + (window.MutationObserver ? "ClientSideMetricsAUIJavascript-51171fbdd28e1a7a61e922e8f0272af8bc74d37b.secure.variant-desktop-session-snapshot-keypress.min._V2_.js" :
                document.addEventListener ? "ClientSideMetricsAUIJavascript-7189c9737ef891e6a0d1587ecfc5b976a156e72f.secure.variant-desktop-mouse-keypress.min._V2_.js" : "ClientSideMetricsAUIJavascript-3cf06f49a56c20b08645a8366b39ece26d761af5.secure.min._V2_.js"))
    }
})(ue_csm);

(function (c, d) {
    c.ue = c.ue || {};
    function b(h, j, i) {
        var g = "; expires=" + new Date(+new Date + i).toGMTString();
        d.cookie = h + "=" + j + g + "; path=/"
    }

    function a(g) {
        if (d.cookie && d.cookie.split) {
            var l = document.cookie.split(";"), j, m, k = new RegExp("^\\s*" + g + "=");
            for (var h = 0; h < l.length; h++) {
                j = l[h];
                if (k.test(j)) {
                    m = j.split("=");
                    if (m && m.length == 2) {
                        return m[1]
                    }
                }
            }
        }
        return null
    }

    function f(g) {
        b(g, "", -1)
    }

    var e = {set: b, get: a, del: f};
    c.ue.ch = e
})(ue_csm, document);
(function (c) {
    var m = c.ue || {}, j = "csm-sid", h = "0123456789", i = "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ";

    function e(o) {
        var p = /^\d{3}-\d{7}-\d{7}$/;
        return p.test(o)
    }

    function n(o) {
        var p = Math.floor(Math.random() * o.length);
        return o.charAt(p)
    }

    function d(r, q) {
        var o = "";
        for (var p = 0; p < q; p++) {
            o = o + n(r)
        }
        return o
    }

    function g() {
        return d(i, 20)
    }

    function k() {
        var o = d(h, 18);
        return o.substring(0, 3) + "-" + o.substring(4, 11) + "-" + o.substring(11, 18)
    }

    function b() {
        if (c.ue && c.ue.ch) {
            var o = c.ue.ch.get(j);
            if (e(o)) {
                return o
            }
        }
        return 0
    }

    function l(o) {
        if (!e(o)) {
            return 0
        }
        if (c.ue && c.ue.ch) {
            c.ue.ch.set(j, o, 2366769449);
            return 1
        }
    }

    var f = {newRID: g, newSID: k, getSID: b, setSID: l};
    m.id = c.ue_id ? c.ue_id : f.newRID();
    m.rid = m.id;
    m.mid = c.ue_mid || "mkt=aiwnacsm";
    m.furl = c.ue_furl || "fls-na.amazon.com";
    m.sn = (c.ue_sn) || (window.location ? window.location.hostname : "");
    var a = c.ue_sid || f.getSID();
    if (!a) {
        a = f.newSID();
        f.setSID(a)
    }
    m.sid = a;
    m.identifier = f;
    c.ue = m
})(ue_csm);
(function (g) {
    var a = g.ue || {}, e = g.ue_modules || ["latency", "forester", "jserrors"], h = 0, b = function () {
    }, d = {};

    function f() {
        var j;
        if (a.q) {
            for (var k = 0; k < a.q.length; k++) {
                j = a.q[k];
                if (j.n && d[j.n] && d[j.n].call) {
                    d[j.n].call(this, j.t, j.a)
                }
            }
        }
        b()
    }

    function c(j, i) {
        var k = b;
        b = function () {
            k();
            i()
        };
        h++;
        if (h == e.length) {
            f()
        }
    }

    a.implementations = d;
    a.register = c;
    g.ue = a
})(ue_csm);
(function (e, l) {
    var m = e.ue || {};
    e.ueinit = (e.ueinit || 0) + 1;
    if (!m.id) {
        m.id = e.ue_id ? e.ue_id : m.identifier.newRID()
    }
    m.url = e.ue_url ? e.ue_url : ("/uedata/" + m.sid + "/");
    m.a = "";
    m.b = "";
    m.h = {};
    m.r = {ld: 0, oe: 0, ul: 0};
    m.s = 1;
    m.t = {};
    m.sc = {};
    m.iel = [];
    m.ielf = [];
    m.fc_idx = {};
    m.viz = [];
    m.v = "a02";
    m.ifr = ((l.top !== l.self) || (l.frameElement)) ? 1 : 0;
    var h = (function () {
        var o = {}, n = [];
        return {
            tag: function (p, r) {
                if (m.tag.delta >= 0) {
                    m.tag.delta = -1
                }
                var q;
                if (typeof r == "undefined") {
                    q = n
                } else {
                    o[r] = o[r] || [];
                    q = o[r]
                }
                q.push(p)
            }, consume: function (q) {
                var p;
                if (typeof q == "undefined") {
                    p = n;
                    n = []
                } else {
                    p = o[q] || [];
                    o[q] = []
                }
                return p
            }
        }
    })();

    function d(o, p, q) {
        if (d.delta >= 0) {
            d.delta = -1
        }
        var r, n;
        if (o) {
            r = n = e.ue;
            if (p && p != r.id) {
                n = r.sc[p];
                if (!n) {
                    n = {};
                    q ? (r.sc[p] = n) : n
                }
            }
            r = q ? (n[o] = q) : n[o]
        }
        return r
    }

    function c(o, s, v, r) {
        if (c.delta >= 0) {
            r = r || m.t0 + c.delta;
            c.delta = -1
        }
        var u = r || (new Date()).getTime();
        var n = !s && typeof v != "undefined";
        if (n) {
            return
        }
        if (o) {
            var p = s ? d("t", s) || d("t", s, {}) : e.ue.t;
            p[o] = u;
            for (var q in v) {
                d(q, s, v[q])
            }
        }
        return u
    }

    function k(q, o, p, n, s) {
        var r = "on" + p;
        var t = o[r];
        if (typeof(t) == "function") {
            if (q) {
                e.ue.h[q] = t
            }
        } else {
            t = function () {
            }
        }
        o[r] = s ? function (u) {
            n(u);
            t(u)
        } : function (u) {
            t(u);
            n(u)
        };
        o[r].isUeh = 1
    }

    function b(t, o, u) {
        var r;
        if (b.delta >= 0) {
            r = m.t0 + b.delta;
            b.delta = -1
        }
        function y(V, T) {
            var R = [V], P = 0, S = {};
            if (T) {
                R.push("m=1");
                S[T] = 1
            } else {
                S = e.ue.sc
            }
            var K;
            for (var L in S) {
                var N = d("wb", L), Q = d("t", L) || {}, O = d("t0", L) || e.ue.t0;
                if (T || N == 2) {
                    var U = N ? P++ : "";
                    R.push("sc" + U + "=" + L);
                    for (var M in Q) {
                        if (M.length <= 3 && Q[M]) {
                            R.push(M + U + "=" + (Q[M] - O))
                        }
                    }
                    R.push("t" + U + "=" + Q[t]);
                    if (d("ctb", L) || d("wb", L)) {
                        K = 1
                    }
                }
            }
            if (!q && K) {
                R.push("ctb=1")
            }
            return R.join("&")
        }

        function B(O, L, N, K) {
            if (O == "") {
                return
            }
            var M = new Image();
            if (e.ue.b) {
                M.onload = function () {
                    if (e.ue.b == "") {
                        return
                    }
                    var Q = e.ue.b;
                    e.ue.b = "";
                    B(Q, L, N, 1)
                }
            }
            var P = !e.ue.log || (!K && !N);
            P = P && e.ue_uselegacy;
            if (P) {
                e.ue.iel.push(M);
                M.src = O
            }
            if (e.ue.log) {
                e.ue.log(O, "uedata", {n: 1});
                e.ue.ielf.push(O)
            }
            if (e.ue_err && !e.ue_err.ts) {
                e.ue_err.startTimer()
            }
        }

        function I(L) {
            if (!m.collected) {
                var M = L.timing;
                if (M) {
                    e.ue.t.na_ = M.navigationStart;
                    e.ue.t.ul_ = M.unloadEventStart;
                    e.ue.t._ul = M.unloadEventEnd;
                    e.ue.t.rd_ = M.redirectStart;
                    e.ue.t._rd = M.redirectEnd;
                    e.ue.t.fe_ = M.fetchStart;
                    e.ue.t.lk_ = M.domainLookupStart;
                    e.ue.t._lk = M.domainLookupEnd;
                    e.ue.t.co_ = M.connectStart;
                    e.ue.t._co = M.connectEnd;
                    e.ue.t.sc_ = M.secureConnectionStart;
                    e.ue.t.rq_ = M.requestStart;
                    e.ue.t.rs_ = M.responseStart;
                    e.ue.t._rs = M.responseEnd;
                    e.ue.t.dl_ = M.domLoading;
                    e.ue.t.di_ = M.domInteractive;
                    e.ue.t.de_ = M.domContentLoadedEventStart;
                    e.ue.t._de = M.domContentLoadedEventEnd;
                    e.ue.t._dc = M.domComplete;
                    e.ue.t.ld_ = M.loadEventStart;
                    e.ue.t._ld = M.loadEventEnd
                }
                var K = L.navigation;
                if (K) {
                    e.ue.t.ty = K.type + e.ue.t0;
                    e.ue.t.rc = K.redirectCount + e.ue.t0;
                    if (e.ue.tag) {
                        e.ue.tag(K.redirectCount ? "redirect" : "nonredirect", m.main_scope)
                    }
                }
                e.ue.collected = 1
            }
        }

        var J = !o && typeof u != "undefined";
        if (J) {
            return
        }
        for (var z in u) {
            d(z, o, u[z])
        }
        c("pc", o, u);
        var H = d("id", o) || e.ue.id;
        var w = !o || (o == H);
        var s = e.ue.url + "?" + t + "&v=" + e.ue.v + "&id=" + H;
        var q = d("ctb", o) || d("wb", o);
        if (q) {
            s += "&ctb=" + q
        }
        if (e.ueinit > 1) {
            s += "&ic=" + e.ueinit
        }
        var F = l.performance || l.webkitPerformance;
        var E = e.ue.bfini;
        var v = F && F.navigation && F.navigation.type == 2;
        if (E && E > 1) {
            s += "&bft=" + (E - 1);
            s += "&bfform=1"
        } else {
            if (v) {
                s += "&bft=1"
            }
        }
        if (v) {
            s += "&bfnt=1"
        }
        if (e.ue._fi && t == "at" && (!o || o == H)) {
            s += e.ue._fi()
        }
        var n;
        if ((t == "ld" || t == "ul") && (!o || o == H)) {
            if (t == "ld") {
                if (l.onbeforeunload && l.onbeforeunload.isUeh) {
                    l.onbeforeunload = null
                }
                if (document.ue_backdetect && document.ue_backdetect.ue_back) {
                    document.ue_backdetect.ue_back.value++
                }
                if (e._uess) {
                    n = e._uess()
                }
            }
            if (F && F.timing) {
                d("ctb", H, "1");
                e.ue.t.tc = F.timing.navigationStart
            }
            if (F) {
                I(F)
            }
            if (e.ue_hob && e.ue_hoe) {
                e.ue.t.hob = e.ue_hob;
                e.ue.t.hoe = e.ue_hoe
            }
            if (e.ue.ifr) {
                s += "&ifr=1"
            }
        }
        c(t, o, u, r);
        var D = (t == "ld" && o && d("wb", o));
        if (D) {
            d("wb", o, 2)
        }
        var G = 1;
        for (var x in e.ue.sc) {
            if (d("wb", x) == 1) {
                G = 0;
                break
            }
        }
        if (D) {
            s = y(s, null)
        } else {
            if (G) {
                var C = y(s, null);
                if (C != s) {
                    e.ue.b = C
                }
            }
            if (n) {
                s += n
            }
            s = y(s, o || e.ue.id)
        }
        if (e.ue.b || D) {
            for (var x in e.ue.sc) {
                if (d("wb", x) == 2) {
                    delete e.ue.sc[x]
                }
            }
        }
        var p = 0;
        if (!D) {
            e.ue.s = 0;
            if (e.ue_err && e.ue_err.ec > 0) {
                s += "&ec=" + e.ue_err.ec
            }
            p = d("ctb", o);
            d("t", o, {})
        }
        if (s) {
            var A = [h.consume().join("|"), h.consume(o).join("|"), (w ? h.consume(m.main_scope).join("|") : "")];
            if (A) {
                s += "&csmtags=" + A.join("|")
            }
        }
        if (s && e.ue_pageviz && e.ue.viz.length > 0) {
            s += "&viz=" + e.ue.viz.join("|");
            e.ue.viz = []
        }
        e.ue.a = s;
        B(s, t, p, D)
    }

    function i() {
        var p = e.ue.r;

        function n(r) {
            return function () {
                if (!p[r]) {
                    p[r] = 1;
                    b(r)
                }
            }
        }

        e.onLd = n("ld");
        e.onLdEnd = n("ld");
        var o = {
            beforeunload: n("ul"), stop: function () {
                b("os")
            }
        };
        for (var q in o) {
            k(0, window, q, o[q])
        }
        if (e.ue_pageviz) {
            ue_viz && ue_viz()
        }
        e.ue._uep = function () {
            new Image().src = (e.ue_md ? e.ue_md : "http://uedata.amazon.com/uedata/?tp=") + (+new Date)
        };
        if (e.ue_pr && (e.ue_pr == 2 || e.ue_pr == 4)) {
            e.ue._uep()
        }
    }

    function g(p, n) {
        var o;
        if (g.delta >= 0) {
            o = m.t0 + g.delta;
            g.delta = -1
        }
        if (!p) {
            return
        }
        e.ue_cel && e.ue_cel.reset();
        e.ue.t0 = o || +new Date();
        e.ue.rid = p;
        e.ue.id = p;
        e.ue.fc_idx = {};
        e.ue.viz = []
    }

    function f(o, p, n) {
        n = n || window;
        if (n.addEventListener) {
            n.addEventListener(o, p, false)
        } else {
            if (n.attachEvent) {
                n.attachEvent("on" + o, p)
            }
        }
    }

    function j(o, p, n) {
        n = n || window;
        if (n.removeEventListener) {
            n.removeEventListener(o, p, false)
        } else {
            if (n.detachEvent) {
                n.detachEvent("on" + o, p)
            }
        }
    }

    function a() {
        e.ues = d;
        e.uet = c;
        e.uex = b;
        e.ue.reset = g;
        e.ue.attach = f;
        e.ue.detach = j
    }

    m.implementations.ues = function (o, n) {
        d.delta = o;
        d.apply(this, n)
    };
    m.implementations.uet = function (o, n) {
        c.delta = o;
        c.apply(this, n)
    };
    m.implementations.uex = function (o, n) {
        b.delta = o;
        b.apply(this, n)
    };
    m.implementations.tag = function (o, n) {
        m.tag.delta = o;
        m.tag.apply(this, n)
    };
    m.implementations.rst = function (o, n) {
        g.delta = o;
        g.apply(this, n)
    };
    m.tag = h.tag;
    m.register("latency", a);
    i()
})(ue_csm, window);