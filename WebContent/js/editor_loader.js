(function () {
    var w = document,
        g = window,
        E = w.documentElement,
        J = false,
        t = true,
        u = null,
        l;
    var G = "",
        j = "tx_",
        f = "uninitialized",
        L = "loading",
        a = "complete",
        x = "production",
        K = "development",
        C = "EDR_ST",
        q = 1000,
        s = 5;
    var A = /\/([6-9][a-z.]?\.[a-z0-9\-]+\.[\-\w]+)\//;
    var c = {
        environment: x,
        service: "core",
        version: "",
        host: ""
    };

    function e(M) {
        return M.replace(/[^\/]+\/?$/, "")
    }

    function b(N) {
        var M = w.getElementsByTagName("script");
        for (var O = 0; O < M.length; O++) {
            if (M[O].src.indexOf(N) >= 0) {
                return M[O]
            }
        }
        throw "cannot find '" + N + "' script element"
    }

    function h(N) {
        var M = b(N);
        var O = M.src;
        return O.substring(O.indexOf("?") + 1)
    }

    function r(N) {
        var M = b(N);
        var O = M.src.match(A);
        if (O && O.length == 2) {
            return O[1]
        }
        return ""
    }

    function F(M) {
        return c[M] || G
    }

    function i(M) {
        var N = o.parse(h(H.NAME), "&");
        return N.findByName(M)
    }

    function D(M) {
        var O = o.parse(w.cookie, /;[ ]*/);
        var N = O.findByName(j + M);
        return N ? decodeURIComponent(N) : N
    }
    var o = function () {
        this.data = []
    };
    o.prototype = {
        add: function (M, N) {
            this.data.push({
                name: M,
                value: N
            })
        },
        findByName: function (M) {
            var O;
            for (var N = 0; N < this.data.length; N++) {
                if (this.data[N] && this.data[N].name === M) {
                    O = this.data[N].value;
                    break
                }
            }
            return O
        }
    };
    o.parse = function (O, Q) {
        var M = new o();
        var R = O.split(Q);
        for (var N = 0; N < R.length; N++) {
            var P = R[N].split("=");
            M.add(P[0], P[1])
        }
        return M
    };

    function n(N) {
        var M = w.createElement("script");
        M.type = "text/javascript";
        M.src = N;
        return M
    }

    function m(O) {
        var M = document.location;
        if (O.match(/^(https?|file):\/\//)) {} else {
            if (O.indexOf("/") === 0) {
                O = "http://" + M.host + O
            } else {
                var N = M.href;
                var P = N.lastIndexOf("/");
                O = N.substring(0, P + 1) + O
            }
        }
        return O
    }

    function p(O, P) {
        var M = n(O);
        var N = w.getElementsByTagName("head")[0] || E;
        y(M, N, P);
        N.insertBefore(M, N.firstChild);
        return M
    }

    function y(M, N, O) {
        if (O) {
            M.onload = M.onreadystatechange = function () {
                if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
                    O();
                    if (/MSIE/i.test(navigator.userAgent)) {
                        M.onload = M.onreadystatechange = u;
                        if (N && M.parentNode) {
                            N.removeChild(M)
                        }
                    }
                }
            }
        }
    }

    function I(M) {
        if (typeof M === "function") {
            M(Editor)
        }
    }

    function k(O) {
        var N = new Image();
        var M = H.getOption("service");
        N.src = "http://rialog.daum-img.net:8080/editor_error/?service=" + M + "&" + O
    }
    var v = function (M) {
        this.TIMEOUT = s * q;
        this.readyState = f;
        this.url = M.url;
        this.callback = M.callback || function () {};
        this.id = M.id;
        this.load()
    };
    v.prototype = {
        load: function () {
            var O = this.url;
            var N = this;
            try {
                b(O)
            } catch (P) {
                N.readyState = L;
                var M = p(O, function () {
                    N.callback();
                    N.readyState = a
                });
                if (N.id) {
                    M.id = N.id
                }
            }
            return this
        },
        startErrorTimer: function () {
            var M = this;
            setTimeout(function () {
                if (M.readyState !== a) {
                    M.onTimeout()
                }
            }, M.TIMEOUT)
        },
        onTimeout: function () {},
        onLoadComplete: function () {}
    };
    var B = [],
        z;
    var H = {
        NAME: "editor_loader.js",
        TIMEOUT: s * q,
        readyState: f,
        loadModule: function (N) {
            function O(P) {
                return !P.match(/^((https?|file):\/\/|\.\.\/|\/)/)
            }
            var M = O(N) ? this.getJSBasePath() + N : N;
            if (c.environment === K) {
                M = M + "?dummy=" + new Date().getTime()
            }
            w.write('<script type="text/javascript" src="' + M + '" charset="utf-8"><\/script>')
        },
        asyncLoadModule: function (M) {
            return new v(M)
        },
        ready: function (M) {
            if (this.readyState === a) {
                I(M)
            } else {
                B.push(M)
            }
        },
        finish: function () {
            if (z) {
                k("message=loading_error&detail=retry_success")
            } else {
                if (document.cookie.indexOf(C + "=e") > -1) {
                    k("message=loading_error&detail=recovered")
                }
            }
            document.cookie = C + "=; expires=Mon, 1 Jan 1970 00:00:00 GMT";
            for (var M = 0; M < B.length; M++) {
                I(B[M])
            }
            B = []
        },
        getBasePath: function (N) {
            var O = D("base_path");
            if (!O) {
                var M = b(N || H.NAME);
                O = e(e(M.src))
            }
            return m(O)
        },
        getJSBasePath: function (M) {
            return this.getBasePath() + "js/"
        },
        getCSSBasePath: function () {
            return this.getBasePath() + "css/"
        },
        getPageBasePath: function () {
            return this.getBasePath() + "someNsome/menu1/pages/"
        },
        getOption: function (M) {
            return D(M) || i(M) || F(M)
        }
    };
    window.EditorJSLoader = H;

    function d() {
        var N = "editor.js";
        c.version = r(H.NAME);
        var M = i("environment");
        if (M) {
            c.environment = M
        }
        H.loadModule(N)
    }
    d()
})();